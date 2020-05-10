import React, { Component } from 'react'
import { faEdit, faTrash} from '@fortawesome/free-solid-svg-icons';
import { Card, ButtonGroup, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';




export default class TaskElement extends Component {

    constructor(props) {
        super(props);

    }



    render() {
        return (
            <Card border="secondary" bg={"dark"} text={"white"} className={"mb-1"}>


                <tr className={"alighnLeft"} key={this.props.id}>
                    <Card.Body>
                        <Card.Title>{this.props.task.taskName}  </Card.Title>
                        <td></td>
                        <Card.Text>{this.props.task.taskDescription} <br /> {this.props.task.taskState} </Card.Text>
                    </Card.Body>
                    <td>
                        <ButtonGroup >
                            <Link to={"taskedit/" + this.props.projectid + "/tasks/" + this.props.task.id}
                                className="btn btn-sm btn-outline-primary"> <FontAwesomeIcon icon={faEdit} />  </Link>{''}

                            <Button size="sm" variant="outline-danger" onClick={this.props.deleteTask.bind(this, this.props.task.id)}>
                                <FontAwesomeIcon icon={faTrash} /> </Button>{''}


                        </ButtonGroup>
                    </td>

                </tr>
            </Card>
        )
    }
}
