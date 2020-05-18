import React from 'react'
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons'
import { Card, ButtonGroup, Button, Badge } from 'react-bootstrap'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Link } from 'react-router-dom'
import TaskElementPopup from './TaskElementPopup'
import { Markup } from 'interweave'
import { useDrag } from 'react-dnd'
import { ItemTypes } from './utils/Items'

export default function TaskElement(props) {
    const [{ isDragging }, drag] = useDrag({
        item: {
            type: ItemTypes.CARD,
            id: props.task.id,
            priority: props.task.taskState,
        },
        collect: (monitor) => ({
            isDragging: !!monitor.isDragging(),
        }),
    })

const priority = <Badge variant="light">{props.task.taskPriority}</Badge>


    return (
      <Card
        ref={drag}
        style={isDragging ? { opacity: "0.2" } : { opacity: "1" }}
        border="secondary"
        bg={"dark"}
        text={"white"}
        className={"mb-1 kortele"}
      >
        <div key={props.id}>
          <TaskElementPopup task={props.task} projectId={props.projectid}>
            <Card.Header>{priority}</Card.Header>
            <Card.Body>
              <Card.Title>
                <Markup content={props.task.taskName} />
              </Card.Title>
              <Card.Text>
                <Markup content={props.task.taskDescription} />
              </Card.Text>
            </Card.Body>
          </TaskElementPopup>
          <ButtonGroup className={"alighnTaskButtons"}>
            <Link
              to={"taskedit/" + props.projectid + "/tasks/" + props.task.id}
              className="btn btn-sm btn-outline-primary"
            >
              <FontAwesomeIcon icon={faEdit} />
            </Link>

            <Button
              size="sm"
              variant="outline-danger"
              onClick={props.deleteTask.bind(this, props.task.id)}
            >
              <FontAwesomeIcon icon={faTrash} />
            </Button>
          </ButtonGroup>
        </div>
      </Card>
    );
}
