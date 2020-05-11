import React, { Component } from 'react';
import { Card, Form, Container, Row, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faAdjust } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import TaskElement from './TaskElement'

toast.configure()
export default class TaskList extends Component {



    constructor(props) {
        super(props);
        const { match: { params } } = this.props;

        this.state = {
            lists: [],
            search: ''
        }
        this.api = `http://localhost:9090/api/projects/${params.projectId}/tasks`;
    }


    componentDidMount() {
        this.getLists();
    }


    getLists() {
        axios.get(this.api)
            .then((data) => {
                this.setState({ lists: data.data });
            })
            .catch((error) => {
                console.log(error)
            })

    };
    handleSearchPrepare = () => {
        this.setState({ listTemp: [...this.state.lists] })
        console.log(this.state.listTemp)
    }

    setInitialState() {
        this.setState({ lists: this.state.listTemp });
    }

    handleSearchInput = (e) => {

        let { value } = e.target;
        if (value === '') {
            this.setInitialState()
        } else {
            let filtered = this.state.listTemp.filter((p) => {
                return p.taskName.toLowerCase().includes(value.toLowerCase())
            })
            this.setState({
                lists: filtered
            })
        }
    }

    deleteTask = (taskId) => {
        let uri = this.api + "/" + taskId
        console.log(uri)
        axios.delete(uri)
            .then(response => {

                if (response != null)
                    toast.warn("✔️ Task has been deleted successfully ")
                this.setState({
                    lists: this.state.lists.filter(x => x.id !== taskId)

                });

            });

    };


    getTaskItems = (taskState) => (
        this.state.lists.length ?
            this.state.lists.map(task => {
                if (task.taskState == taskState)
                    return (
                        <TaskElement key={task.id} task={task} list={this.state.lists} projectid={this.props.match.params.projectId} api={this.api} deleteTask={this.deleteTask} />
                    )
            })
            :
            null

    )


    render() {
        return (

            <Container>


                < Card className={"border border-dark bg-dark text-white"} >
                    <Card.Body>
                        <Container >
                            <Row>
                                <Col >
                                    <FontAwesomeIcon icon={faList} />  Task list
                            </Col>
                                <Col md="auto">
                                    <Form.Control className={"search-width"}  onChange={this.handleSearchInput} onFocus={this.handleSearchPrepare}  placeholder="Search ..." />
                                </Col>
                                <Col xs lg="2">
                                    <Link to={"addtask/" + this.props.match.params.projectId} className="btn btn-sm btn-outline-light"> Add task <FontAwesomeIcon icon={faAdjust} />  </Link>{''}
                                </Col>
                            </Row>
                        </Container>


                        <Row style={{ border: '1px solid #49a75f0d' }}>

                            <Col >
                                <div className={"centerText"} >To do </div>
                                <br />
                                {this.getTaskItems("TO_DO")}

                            </Col>
                            <Col>
                                <div className={"centerText"}>In progress </div>
                                <br />
                                {this.getTaskItems("IN_PROGRESS")}
                            </Col>
                            <Col>
                                <div className={"centerText"}> Done </div>
                                <br />
                                {this.getTaskItems("DONE")}
                            </Col>
                        </Row>


                    </Card.Body>
                </Card >

            </Container>
        );

    }
}