import React, { Component } from 'react';
import { Card, Table, ButtonGroup, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faEdit, faTrash, faAdjust } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { Link } from 'react-router-dom';


export default class List extends Component {


    constructor(props) {
        super(props);
        this.state = {
            lists: []
        }
        this.api = "http://localhost:9090/api/projects";

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

    deleteProject = (projectId) => {

        axios.delete(this.api + "/" + projectId)
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
                <Card.Header><FontAwesomeIcon icon={faList} /> All projects list </Card.Header>
                <Card.Body>
                    <Table bordered hover striped variant="dark">
                        <thead>
                            <tr>
                                <td>Project Name</td>
                                <td>Project Description</td>
                                <td>Project Status</td>
                                <td>Project Id</td>
                                <td>Actions</td>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                lists.length ?
                                    lists.map(project => {
                                        return (
                                            <tr key={project.id}>
                                                <td>{project.projectName} </td>
                                                <td>{project.projectDescription} </td>
                                                <td> {project.projectStatus}</td>
                                                <td>{project.id} </td>
                                                <td>
                                                    <ButtonGroup>
                                                        <Link to={"edit/" + project.id} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faEdit} />  </Link>{''}

                                                        <Button size="sm" variant="outline-danger" onClick={this.deleteProject.bind(this, project.id)}>
                                                            <FontAwesomeIcon icon={faTrash} /> </Button>{''}
                                                        <Link to={"addtask"} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faAdjust} />  </Link>{''}
                                                        <Link to={"tasklist/"} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faList} />  </Link>{''}
                                                        {
                                                            /* Reik padaryti kazkaip, kad linkintu su mygtuku i task lists puslapi
    
                                                       */
                                                        }
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