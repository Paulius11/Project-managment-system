import React, { Component, useState } from 'react';
import { Card, Table, ButtonGroup, Badge, Form, Button, Pagination, Container, Row, Col, Modal } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faEdit, faTrash, faAdjust, faPlusSquare, faFileExport } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../App.css';
import { Markup } from 'interweave';
import ReactTooltip from "react-tooltip";
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';

toast.configure()
export default class List extends Component {


    constructor(props) {
        super(props);

        this.state = {
            lists: [],
            search: '',
            currentPage: 1,
            postsPerPage: 15
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
                toast.success("Project has been deleted successfully")
                if (response != null)

                    this.setState({
                        lists: this.state.lists.filter(x => x.id !== projectId)

                    });

            });


    };

    setInitialState() {
        this.setState({ lists: this.state.listTemp });
    }

    handleSearchInput = (e) => {

        let { value } = e.target;
        if (value === '') {
            this.setInitialState()

        } else {
            let filtered = this.state.listTemp.filter((p, a) => {
                return p.projectName.toLowerCase().includes(value.toLowerCase())
            })
            this.setState({
                currentPage: 1,
                lists: filtered
            })
        }
    }

    handleSearchPrepare = () => {
        this.setState({ listTemp: [...this.state.lists] })
    }

    paginate = (page) => {
        this.setState({ currentPage: page })
        console.log(this.state.currentPage)
    }

    changeDisplayProjectCount = (event) => {
        console.log(event.target)

        this.setState({
            [event.target.name]: event.target.value,
            postsPerPage: event.target.value
        });
        console.log(this.state)
    }


    getProjectCsv = (projectId) => {

        axios.get(this.api + `/${projectId}/export-projects`)
        const method = 'GET';

        const url = this.api + `/${projectId}/export-projects`;

        axios

            .request({

                url,

                method,

                responseType: 'blob', //important

            })

            .then(({ data }) => {

                const downloadUrl = window.URL.createObjectURL(new Blob([data]));

                const link = document.createElement('a');

                link.href = downloadUrl;

                link.setAttribute('download', 'projects.csv'); //any other extension

                document.body.appendChild(link);

                link.click();

                link.remove();

            });

    }

    submit = (projectId) => {
        confirmAlert({
            title: 'Confirm to delete project',
            message: 'Are you sure to delete it?.',
            buttons: [
                {
                    label: 'Yes',
                    onClick: () => {
                        this.deleteProject(projectId)
                        console.log(projectId)
                    }


                },
                {
                    label: 'No'
                }
            ]
        });
    };



    render() {

        const { lists } = this.state;


        //Modal should pop up as confirmation

        // Get current posts -- pagination
        const indexOfLastPost = this.state.currentPage * this.state.postsPerPage;
        const indexOfFirstPost = indexOfLastPost - this.state.postsPerPage;
        const currentPosts = lists.slice(indexOfFirstPost, indexOfLastPost);


        let items = [];
        for (let number = 1; number <= Math.ceil(lists.length / this.state.postsPerPage); number++) {
            items.push(
                <Pagination.Item onClick={() => this.paginate(number)} key={number} >
                    {number}
                </Pagination.Item>,
            );
        }

        const paginationBasic = (
            <div>
                <Pagination bsPrefix={'custom-pagination'}>{items}</Pagination>

            </div>
        );

        const listOfItems =
            (

                lists.length ?
                    currentPosts.map(project => {
                        return (

                            <tr key={project.id}>

                                <td>{project.id} </td>
                                <td>{project.projectName} </td>
                                <td><Markup content={project.projectDescription} /></td>
                                <td align="center"> {project.projectStatus === "ACTIVE" ? <Badge pill variant="success">Active</Badge> : <Badge pill variant="secondary">Completed</Badge>} </td>
                                <td align="center"> {project.incopleteTasks === 0 ? <Badge pill variant="success"><p className="logo" data-tip={`${project.incopleteTasks}/${project.totalTasks}`} >✔</p><ReactTooltip /></Badge> : <Badge pill variant="secondary" ><p className="logo" data-tip={`${project.incopleteTasks}/${project.totalTasks}`}>✘</p><ReactTooltip /></Badge>}</td>

                                <td>
                                    <ButtonGroup>
                                        <Link to={"edit/" + project.id} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faEdit} />  </Link>{''}

                                        <Button size="sm" variant="outline-danger" onClick={this.submit.bind(this, project.id)}>
                                            <FontAwesomeIcon icon={faTrash} /> </Button>{''}

                                        <Link to={"tasklist/addtask/" + project.id} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faAdjust} />  </Link>{''}
                                        <Link to={"tasklist/" + project.id} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faList} />  </Link>{''}
                                        <Button size="sm" variant="outline-success" onClick={this.getProjectCsv.bind(this, project.id)}> <FontAwesomeIcon icon={faFileExport} /></Button>

                                    </ButtonGroup>
                                </td>
                            </tr >)
                    })
                    :
                    null

            )

        let paginationForm = (<Form.Control as="select" size="sm" name="paginationForm" value={this.state.postsPerPage} className={"bg-dark text-white pagination-options"} onChange={this.changeDisplayProjectCount} custom>
            <option>3</option>
            <option>15</option>
            <option>20</option>
            <option>25</option>
            <option>50</option>
        </Form.Control>)

        return (

            <Card className={"border border-dark bg-dark text-white"} >

                <Card.Body>
                    <Container >
                        <Row>
                            <Col >
                                <FontAwesomeIcon icon={faList} /> List of projects:   <Badge pill variant="light">{lists.length}</Badge>{' '}

                            </Col>
                            <Col md="auto">
                                <Form.Control className={"search-width"} onChange={this.handleSearchInput} onFocus={this.handleSearchPrepare} placeholder="Search ..." />
                            </Col>
                            <Col xs lg="2">
                                <Link to={"add/"} className="btn btn-sm btn-outline-light"> Add project <FontAwesomeIcon icon={faPlusSquare} /></Link>
                            </Col>
                        </Row>
                    </Container>

                    <Table bordered hover striped variant="dark">

                        <thead>
                            <tr>
                                <td>Id</td>
                                <td>Project Name</td>
                                <td>Project Description</td>
                                <td>Project Status</td>
                                <td>Tasks</td>
                                <td>Actions</td>
                            </tr>
                        </thead>
                        <tbody>
                            {listOfItems}
                            <br />
                        </tbody>

                    </Table>
                </Card.Body>




                <Container>
                    <Row className="justify-content-md-center mb-4" >
                        <Col xs lg="2">
                        </Col>
                        <Col md="auto">{paginationBasic}</Col>
                        <Col xs lg="2">
                            {paginationForm}
                        </Col>
                    </Row>
                </Container>

            </Card >


        );

    }

}