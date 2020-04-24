import React, { Component } from 'react';
import { Card, Table, ButtonGroup, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faEdit, faTrash, faAdjust } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { Link } from 'react-router-dom';


export default class TaskList extends Component {


    constructor(props) {
        super(props);
        this.state = {
            lists: []
        }
        this.api = "http://localhost:9090/api/projects/44/tasks"; // Kazkaip perduoti reik {projectId} 


    }
    componentDidMount() {

        this.getLists();


    }

    getLists() {
        axios.get(this.api)
            .then((data) => {
                this.setState({ lists: data.data });

                console.log(this.state);
            })
            .catch((error) => {
                console.log(error)
            })

    };

    deleteProject = (projectId) => {

        axios.delete(this.api)
            .then(response => {

                if (response != null)
                    alert("Deleted successfully");
                this.setState({
                    lists: this.state.lists.filter(x => x.id !== projectId)

                });

            });

    };

    render() {
        const { lists } = this.state;

        return (

            < Card className={"border border-dark bg-dark text-white"} >
                <Card.Header><FontAwesomeIcon icon={faList} />  Task list </Card.Header>
                <Card.Body>
                    <Table bordered hover striped variant="dark">
                        <thead>
                            <tr>
                                <td>Task Id</td>
                                <td>Task Name</td>
                                <td>Task Description</td>
                                <td>Task Status</td>
                                <td>Task Priority</td>
                                <td>Task Create Time</td>
                                <td>Task Modify Time</td>
                                <td>Actions</td>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                lists.length ?
                                    lists.map(task => {
                                        return (

                                            <tr key={task.id}>
                                                <td>{task.id}{console.log(task.id)}</td>
                                                <td>{task.taskName} </td>
                                                <td>{task.taskDescription} </td>
                                                <td>{task.taskPriority}</td>
                                                <td>{task.taskState} </td>
                                                <td>{task.taskCreateTime}</td>
                                                <td>{task.taskModifyTime}</td>
                                                <td>
                                                    <ButtonGroup>
                                                        <Link to={"edit/" + task.id} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faEdit} />  </Link>{''}

                                                        <Button size="sm" variant="outline-danger" onClick={this.deleteProject.bind(this, task.id)}>
                                                            <FontAwesomeIcon icon={faTrash} /> </Button>{''}
                                                        <Link to={"addtask"} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faAdjust} />  </Link>{''}

                                                    </ButtonGroup>
                                                </td>
                                            </tr>)
                                    })
                                    :
                                    null

                            }
                        </tbody>

                    </Table>
                </Card.Body>
            </Card >
        );

    }
}