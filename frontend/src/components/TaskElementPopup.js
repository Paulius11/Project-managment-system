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

            <Row>Task ID: {props.task.id}</Row>
            <Row>Task Name: {props.task.taskName}</Row>
            <Row>Task Description: {props.task.taskDescription}</Row>
            <Row>Task Priority: {props.task.taskPriority}</Row>
            <Row>Task State: {props.task.taskState}</Row>
            <Row>Task CreateTime: {props.task.taskCreateTime}</Row>
            <Row>Task Modify Time: {props.task.taskModifyTime}</Row>


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

