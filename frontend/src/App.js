import React from 'react';
import './App.css';
import NavigationBar from './components/NavigationBar'
import { Container, Row, Col } from 'react-bootstrap';
import Welcome from './components/Welcome';
import Footer from './components/Footer';
import Project from './components/Project';
import List from './components/List';
import Task from './components/Task';
import TaskList from './components/TaskList';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

function App() {

    const marginTop = {
        marginTop: "20px"
    };



    return (
        <Router>
            <NavigationBar />
            <Container>
                <Row>
                    <Col lg={12} style={marginTop}>
                        <Switch>
                            <Route path="/" exact component={Welcome} />


                            <Route path="/add" exact component={Project} />
                            <Route path="/edit/:projectId" exact component={Project} />
                            <Route path="/list" exact component={List} />

                            <Route path="/addtask/:projectId" exact component={Task} />
                            <Route path="/tasklist/addtask" exact component={Task} />
                            <Route path="/tasklist/taskedit/:taskId" exact component={Task} />
                            <Route path="/tasklist/:projectId" exact component={TaskList} />

                        </Switch>
                    </Col>
                </Row>
            </Container>
            <Footer />
        </Router>
    );
}

export default App;
