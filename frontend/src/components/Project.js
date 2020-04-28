import React, { Component } from 'react';
import { Card, Form, Button, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSave, faUndo, faSitemap, faList } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';


export default class Project extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.projectChange = this.projectChange.bind(this);
        this.submitProject = this.submitProject.bind(this);
    }


    initialState = {

        projectName: '', projectDescription: '', projectStatus: '', projectId: ''
    }

    submitProject(event) {

        event.preventDefault();

        const project = {
            projectName: this.state.projectName,
            projectDescription: this.state.projectDescription,
            projectStatus: this.state.projectStatus,
            projectId: this.state.projectId
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


    findProjectById = (projectId) => {

        axios.get("http://localhost:9090/api/projects/" + projectId)
            .then(response => {
                this.setState({
                    projectName: response.data.projectName,
                    projectDescription: response.data.projectDescription,
                    projectStatus: response.data.projectStatus,
                    projectId: response.data.projectId


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
    resetProject = () => {

        this.setState(() => this.initialState)
    }

    projectList = () => {

        return this.props.history.push("/list");
    };


    render() {

        const { projectName, projectDescription, projectStatus, projectId } = this.state;
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> <FontAwesomeIcon icon={faSitemap} /> Add new project </Card.Header>

                <Form onReset={this.resetProject} onSubmit={this.submitProject} id="projectFormId">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridProjectName">
                                <Form.Label>Project Name</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" name="projectName"
                                    value={projectName}
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter project name" />
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGridProjectDescription">
                                <Form.Label>Project Description</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" name="projectDescription"
                                    value={projectDescription}
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Description" />
                            </Form.Group>


                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridProjectStatus">
                                <Form.Label>Project Status</Form.Label>
                                <Form.Control as="select" required autoComplete="off"
                                    type="text" name="projectStatus"
                                    value={projectStatus}
                                    onChange={this.projectChange}
                                    className={"bg-dark text-white"}>
                                    <option></option>
                                    <option>Not started</option>
                                    <option>In progress</option>
                                    <option>Done</option>
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>


                    </Card.Body>
                    <Card.Footer>
                        <Button size="sm" variant="success" type="submit">
                            <FontAwesomeIcon icon={faSave} />  Submit
                        </Button>{'  '}

                        <Button size="sm" variant="info" type="reset">
                            <FontAwesomeIcon icon={faUndo} />  Reset
                        </Button>{'  '}
                        <Button size="sm" variant="info" type="button" onClick={this.projectList.bind()}>
                            <FontAwesomeIcon icon={faList} />  Project List
                        </Button>{'  '}

                    </Card.Footer>
                </Form>
            </Card>
        );

    }

}
