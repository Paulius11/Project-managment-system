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



    handleSearchInput = (e) => {
        console.log(e.target.value);
        if (e.target.value === '') {
            this.getLists();
        }
        this.setState({
            search: e.target.value,
            lists: this.state.lists.filter((p) => {
                return p.taskName.toLowerCase().includes(this.state.search.toLowerCase())
            })
        })
    };

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
        var divStyle = {
            width: '33%'
        };


        return (

            <Container>


                < Card className={"border border-dark bg-dark text-white"} >
                    <Card.Header><FontAwesomeIcon icon={faList} />  Task list

                    <Form>


                            <Form.Row>

                                <Form.Control style={divStyle} onChange={this.handleSearchInput} placeholder="Search ..." />

                            </Form.Row>

                        </Form>

                    </Card.Header>



                    <Card.Body>
                        <Link to={"addtask/" + this.props.match.params.projectId} className="btn btn-sm btn-outline-light"> Add task <FontAwesomeIcon icon={faAdjust} />  </Link>{''}
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