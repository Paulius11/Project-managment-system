import React, { Component } from 'react';
import { Card, Table, ButtonGroup, Button, Form, Container, Row, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faEdit, faTrash, faAdjust } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


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

    deleteTask = (taskId) => {

        axios.delete(this.api + "/" + taskId)
            .then(response => {

                if (response != null)
                    toast.warn("Task has been deleted successfully")
                this.setState({
                    lists: this.state.lists.filter(x => x.id !== taskId)

                });

            });

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

    getItems = (taskState) => (
        this.state.lists.length ?
            this.state.lists.map(task => {
                if (task.taskState == taskState)
                    return (
                        <Card border="secondary" bg={"dark"} text={"white"} >
                        <tr className={"alighnLeft"} key={task.id}>
                           

                            <Card.Body>
                            <Card.Title>{task.taskName}  </Card.Title>
                            <td></td> 
                            <Card.Text>{task.taskDescription} <br/> {task.taskState} </Card.Text>
                            </Card.Body>


                            <td>
                                <ButtonGroup >
                                    <Link to={"taskedit/" + this.props.match.params.projectId + "/tasks/" + task.id}
                                        className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faEdit} />  </Link>{''}

                                    <Button size="sm" variant="outline-danger" onClick={this.deleteTask.bind(this, task.id)}>
                                        <FontAwesomeIcon icon={faTrash} /> </Button>{''}


                                </ButtonGroup>
                            </td>
                        </tr>
                        </Card>)
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

                            <Link to={"addtask/" + this.props.match.params.projectId} className="btn btn-sm btn-outline-light"> Add task<FontAwesomeIcon icon={faAdjust} />  </Link>{''}
                            <Form.Row>

                                <Form.Control style={divStyle} onChange={this.handleSearchInput} placeholder="Search ..." />

                            </Form.Row>

                        </Form>

                    </Card.Header>



                    <Card.Body>

                        <Row style={{ border: '1px solid #49a75f0d' }}>

                            <Col >
                                <th className={"centerText"} >To do </th>
                                <br />
                                {this.getItems("TO_DO")}

                            </Col>
                            <Col>
                                <th className={"centerText"}>In progress </th>
                                <br />
                                {this.getItems("IN_PROGRESS")}
                            </Col>
                            <Col>
                                <th className={"centerText"}> Done </th>
                                <br />
                                {this.getItems("DONE")}
                            </Col>
                        </Row>


                    </Card.Body>
                </Card >
            </Container>
        );

    }
}