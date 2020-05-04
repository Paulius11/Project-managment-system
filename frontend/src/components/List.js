import React, { Component } from 'react';
import { Card, Table, ButtonGroup, Badge, Form, Button, Pagination, Container, Row, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faEdit, faTrash, faAdjust } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../App.css';

toast.configure()
export default class List extends Component {


    constructor(props) {
        super(props);
        this.state = {
            lists: [],
            search: '',
            currentPage: 1,
            postsPerPage: 3
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

    handleSearchInput = (e) => {
        console.log(e.target.value);
        if (e.target.value === '') {
            this.getLists();
        }
        this.setState({
            currentPage: 1,
            search: e.target.value,
            lists: this.state.lists.filter((p) => {
                return p.projectName.toLowerCase().includes(this.state.search.toLowerCase())
            })
        })
    };

    paginate = (page) => {
        this.setState({ currentPage: page })
        console.log(this.state.currentPage)
    }


    render() {

        const { lists } = this.state;
        var divStyle = {
            width: '33%'
        };


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

        return (

            <Card className={"border border-dark bg-dark text-white"} >

                <Card.Header><FontAwesomeIcon icon={faList} /> All projects list
                    <Form>
                        <Form.Row>

                            <Form.Control style={divStyle} onChange={this.handleSearchInput} placeholder="Search ..." />

                        </Form.Row>

                    </Form>
                </Card.Header>
                <Card.Body>
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
                            {
                                lists.length ?
                                    currentPosts.map(project => {
                                        return (
                                            <tr key={project.id}>
                                                <td>{project.id} </td>
                                                <td>{project.projectName} </td>
                                                <td>{project.projectDescription} </td>
                                                <td align="center"> {project.projectState == "ACTIVE" ? <Badge pill variant="success">Active</Badge> : <Badge pill variant="secondary">Completed</Badge>} </td>
                                                <td align="center"> {project.totalTasks}/{project.incopleteTasks}</td>

                                                <td>
                                                    <ButtonGroup>
                                                        <Link to={"edit/" + project.id} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faEdit} />  </Link>{''}

                                                        <Button size="sm" variant="outline-danger" onClick={this.deleteProject.bind(this, project.id)}>
                                                            <FontAwesomeIcon icon={faTrash} /> </Button>{''}
                                                        <Link to={"tasklist/addtask/" + project.id} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faAdjust} />  </Link>{''}
                                                        <Link to={"tasklist/" + project.id} className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faList} />  </Link>{''}

                                                    </ButtonGroup>
                                                </td>
                                            </tr>)
                                    })
                                    :
                                    null

                            }
                            <br />


                        </tbody>

                    </Table>
                </Card.Body>

                <Container>

                    <Row>
                        <Col md={{ span: 3, offset: 5 }}> {paginationBasic}</Col>

                    </Row>

                </Container>
            </Card >


        );

    }

}