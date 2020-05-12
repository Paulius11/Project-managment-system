import React, { useState } from 'react'
import { Modal, Button, Container, Row, Col } from 'react-bootstrap';
import { faEye } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
export default function TaskElementPopup(props) {

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  function getVariant(taskPriority) {
    if (taskPriority == "LOW")
      return "outline-secondary"
    if (taskPriority == "NORMAL")
      return "outline-success"
    if (taskPriority == "HIGH")
      return "outline-danger"
  }

  return (
    <>

      <Button className={"p-0"} variant={getVariant(props.task.taskPriority)} onClick={handleShow}>
        <FontAwesomeIcon icon={faEye} onClick={handleShow} />
      </Button>
      <Modal show={show} onHide={handleClose} animation={false}>
        <Modal.Header closeButton>
          <Modal.Title>{props.task.taskName}</Modal.Title>
        </Modal.Header>
        <Modal.Body>

          <Container>
            <Row>
              <Col><b>Task ID:</b></Col>
              <Col>{props.task.id}</Col>
            </Row>
            <Row>
              <Col><b>Task Name:</b></Col>
              <Col>{props.task.taskName}</Col>
            </Row>
            <Row>
              <Col><b>Task Description:</b></Col>
              <Col>{props.task.taskDescription}</Col>
            </Row>
            <Row>
              <Col><b>Task Priority:</b></Col>
              <Col>{props.task.taskPriority}</Col>
            </Row>
            <Row>
              <Col><b>Task State:</b></Col>
              <Col>{props.task.taskState}</Col>
            </Row>
            <Row>
              <Col><b>Task Create Time:</b></Col>
              <Col>{props.task.taskCreateTime}</Col>
            </Row>
            <Row>
              <Col><b>Task Modify Time:</b></Col>
              <Col>{props.task.taskModifyTime}</Col>
            </Row>
          </Container>




        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

