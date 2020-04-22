import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBriefcase } from '@fortawesome/free-solid-svg-icons';

class NavigationBar extends React.Component {

	render() {

		return (

			<Navbar bg="success" variant="dark">
				<Link to={""} className="navbar-brand">
					<FontAwesomeIcon icon={faBriefcase} />
					<Navbar.Brand href="#home"> Project management system</Navbar.Brand>
				</Link>

				<Nav className="mr-auto">
					<Link to={"add"} className="nav-link">Add Project</Link>
					<Link to={"list"} className="nav-link">Project List</Link>
					<Link to={"addTask"} className="nav-link">Add Task</Link>
				</Nav>
			</Navbar>


		)
	}
}
export default NavigationBar;