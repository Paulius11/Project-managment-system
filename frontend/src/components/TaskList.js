import React, { Component } from "react";
import { Card, Form, Container, Row, Col, Badge } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faList, faAdjust } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import TaskElement from "./TaskElement";
import TaskBoxTarget from "./TaskBoxTarget";
import Backend from "react-dnd-html5-backend";
import { DndProvider } from "react-dnd";
import ReactTooltip from "react-tooltip";
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';

toast.configure();
export default class TaskList extends Component {
    constructor(props) {
        super(props);
        const {
            match: { params },
        } = this.props;

        this.state = {
            lists: [],
            search: "",
        };
        this.projectId = params.projectId;
        this.api = `http://localhost:9090/api/projects/${this.projectId}/tasks`;
    }

    componentDidMount() {
        this.getLists();
    }

    getLists = () => {
        console.log("Entering function");
        axios
            .get(this.api)
            .then((data) => {
                this.setState({ lists: data.data });
            })
            .catch((error) => {
                console.log(error);
            });
    };
    handleSearchPrepare = () => {
        this.setState({ listTemp: [...this.state.lists] });
        console.log(this.state.listTemp);
    };

    setInitialState() {
        this.setState({ lists: this.state.listTemp });
    }

    handleSearchInput = (e) => {
        let { value } = e.target;
        if (value === "") {
            this.setInitialState();
        } else {
            let filtered = this.state.listTemp.filter((p) => {
                return p.id.toString().includes(value) || p.taskName.toLowerCase().includes(value.toLowerCase())
            });
            this.setState({
                lists: filtered,
            });
        }
    };

    deleteTask = (taskId) => {
        let uri = this.api + "/" + taskId;
        console.log(uri);
        axios.delete(uri).then((response) => {
            if (response != null)
                toast.warn("✔️ Task has been deleted successfully ");
            this.setState({
                lists: this.state.lists.filter((x) => x.id !== taskId),
            });
        });
    };


    submit = (id) => {
        confirmAlert({
            title: 'Confirm to delete this task',
            message: 'Are you sure to delete it?.',
            buttons: [
                {
                    label: 'Yes',
                    onClick: () => this.deleteTask(id)
                },
                {
                    label: 'No'
                }
            ]
        });
    };



    getTaskItems = (taskState) =>
        this.state.lists.length
            ? this.state.lists.map((task) => {
                if (task.taskState == taskState)
                    return (
                        <TaskElement
                            key={task.id}
                            task={task}
                            list={this.state.lists}
                            projectid={this.projectId}
                            api={this.api}
                            deleteTask={this.submit.bind(task.id)}
                        />
                    );
            })
            : null;

    render() {
        return (
            <Container>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Body>
                        <Container>
                            <Row>
                                <Col>
                                    <a data-tip data-for="listOftasks">
                                        <FontAwesomeIcon icon={faList} /> List of tasks:{" "}
                                        <Badge pill variant="light">
                                            {this.state.lists.length}
                                        </Badge>{" "}
                                    </a>
                                    <ReactTooltip id="listOftasks" effect="float">
                                        <p>Project ID: {this.projectId}</p>
                                    </ReactTooltip>
                                </Col>
                                <Col md="auto">
                                    <Form.Control
                                        className={"search-width"}
                                        onChange={this.handleSearchInput}
                                        onFocus={this.handleSearchPrepare}
                                        placeholder="Search ..."
                                    />
                                </Col>
                                <Col xs lg="2">
                                    <Link
                                        to={"addtask/" + this.projectId}
                                        className="btn btn-sm btn-outline-light"
                                    >
                                        {" "}
                                        Add task <FontAwesomeIcon icon={faAdjust} />{" "}
                                    </Link>
                                    {""}
                                </Col>
                            </Row>
                        </Container>

                        <Row style={{ border: "1px solid #49a75f0d" }}>
                            <DndProvider backend={Backend}>
                                <Col>
                                    <TaskBoxTarget
                                        projectId={this.projectId}
                                        getTasks={this.getLists}
                                        boxPriority={"TO_DO"}
                                    >
                                        <div className={"centerText task-status "}>TO DO</div>
                                        {this.getTaskItems("TO_DO")}
                                    </TaskBoxTarget>
                                </Col>

                                <Col>
                                    <TaskBoxTarget
                                        projectId={this.projectId}
                                        getTasks={this.getLists}
                                        boxPriority={"IN_PROGRESS"}
                                    >
                                        <div className={"centerText task-status"}>IN PROGRESS</div>
                                        {this.getTaskItems("IN_PROGRESS")}
                                    </TaskBoxTarget>
                                </Col>
                                <Col>
                                    <TaskBoxTarget
                                        projectId={this.projectId}
                                        getTasks={this.getLists}
                                        boxPriority={"DONE"}
                                    >
                                        <div className={"centerText task-status"}>DONE</div>
                                        {this.getTaskItems("DONE")}
                                    </TaskBoxTarget>
                                </Col>
                            </DndProvider>
                        </Row>
                    </Card.Body>
                </Card>
            </Container>
        );
    }
}
