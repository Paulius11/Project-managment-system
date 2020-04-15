import React, { Component } from 'react';
import { Card, Form, Button, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSave, faUndo, faSitemap, faList } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';


export default class Task extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.projectChange = this.projectChange.bind(this);
        this.submitTask = this.submitTask.bind(this);
    }


    initialState = {

        taskName: '', taskDescription: '', taskPriority: '',
        taskState: '', taskModifyTime: '', taskCreateTime: '' // Initial State  yra tusti laukai, kai paspaudziamas bus reset mygtukas, jis paduos situs argumentus ir formos bus nunulintos
    }

    submitTask(event) {

        event.preventDefault();

        const project = {
            taskName: this.state.taskName,
            taskDescription: this.state.taskDescription,
            taskPriority: this.state.taskPriority,
            taskState: this.state.taskState,
            taskModifyTime: this.state.taskModifyTime,
            taskCreateTime: this.state.taskCreateTime
        };

        axios.post("http://localhost:9090/api/projects", project)
            .then(response => {
                if (response.data != null) {
                    this.setState(this.initialState);
                }
            });


    }
    componentDidMount() {
        const projectId = this.props.match.params.projectId;
        if (projectId) {
            this.findProjectById(projectId);
        }

    };


    findProjectById = (taskId) => {

        axios.get("http://localhost:9090/api/projects/" + taskId) // Reiks pakeist link
            .then(response => {
                this.setState({
                    taskName: response.data.taskName,
                    taskDescription: response.data.taskDescription,
                    taskPriority: response.data.taskPriority,
                    taskState: response.data.taskState,
                    taskModifyTime: response.data.taskModifyTime,
                    taskCreateTime: response.data.taskCreateTime


                });

            }).catch((error) => {
                console.log("Error - " + error);
            });

    };


    projectChange = (event) => {

        this.setState({
            [event.target.name]: event.target.value
        });
    }
    resetTask = () => {

        this.setState(() => this.initialState)
    }

    taskList = () => {

        return this.props.history.push("/list"); // App js , kai bus Task List sita reiks pakeist i /tasklist arba koks ten bus parasytas route path

    };


    render() {

        const { taskName, taskDescription, taskState, taskPriority, taskCreateTime, taskModifyTime } = this.state;
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> <FontAwesomeIcon icon={faSitemap} /> Add new task </Card.Header>

                <Form onReset={this.resetTask} onSubmit={this.submitTask} id="taskFormId">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridTasktName">
                                <Form.Label>Task Name</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" name="taskName"
                                    value={taskName}
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter project name" />
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGridTaskDescription">
                                <Form.Label>Task Description</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" name="taskDescription"
                                    value={taskDescription}
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Description" />
                            </Form.Group>


                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridTaskPriority">
                                <Form.Label>Task Priority</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" name="taskPriority"
                                    value={taskPriority}
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Status" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formGridTaskState">
                                <Form.Label>Tast State</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" name="taskState"
                                    value={taskState}
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Status" />
                            </Form.Group>
                        </Form.Row>

                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridTaskModifyTime">
                                <Form.Label>Task Modify Time</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" name="taskModifyTime"
                                    value={taskModifyTime}
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Status" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formGridTaskCreateTime">
                                <Form.Label> Task Create Time</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" name="taskCreateTime"
                                    value={taskCreateTime}
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Status" />
                            </Form.Group>
                        </Form.Row>


                    </Card.Body>
                    <Card.Footer>
                        <Button size="sm" variant="success" type="submit">
                            <FontAwesomeIcon icon={faSave} />  Submit
                        </Button>{''}

                        <Button size="sm" variant="info" type="reset">
                            <FontAwesomeIcon icon={faUndo} />  Reset
                        </Button>
                        <Button size="sm" variant="info" type="button" onClick={this.taskList.bind()}>
                            <FontAwesomeIcon icon={faList} />  Project List
                        </Button>
                    </Card.Footer>
                </Form>
            </Card>
        );

    }

}
