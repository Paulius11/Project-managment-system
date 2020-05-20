import React, { useState } from "react";
import { Modal, Button, Container, Row, Col } from "react-bootstrap";
import { Markup } from "interweave";
import { Link } from "react-router-dom";

export default function TaskElementPopup(props) {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  function getVariant(taskPriority) {
    if (taskPriority == "LOW") return "outline-secondary";
    if (taskPriority == "NORMAL") return "outline-success";
    if (taskPriority == "HIGH") return "outline-danger";
  }

  return (
    <>
      <div
        className={"p-0"}
        variant={getVariant(props.task.taskPriority)}
        onClick={handleShow}
      >
        {props.children}
      </div>
      <Modal show={show} onHide={handleClose} animation={false}>
        <Modal.Header closeButton>
          <Modal.Title>
            <Col>
              <Markup content={props.task.taskName} />
            </Col>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Container>
            <Row>
              <Col>
                <b>Project ID:</b>
              </Col>
              <Col>{props.projectId}</Col>
            </Row>
            <Row>
              <Col>
                <b>Task ID:</b>
              </Col>
              <Col>{props.task.id}</Col>
            </Row>
            <Row>
              <Col>
                <b>Task Description:</b>
              </Col>
              <Col>
                <Markup content={props.task.taskDescription} />
              </Col>
            </Row>
            <Row>
              <Col>
                <b>Task Priority:</b>
              </Col>
              <Col>{props.task.taskPriority}</Col>
            </Row>
            <Row>
              <Col>
                <b>Task State:</b>
              </Col>
              <Col>{props.task.taskState}</Col>
            </Row>
            <Row>
              <Col>
                <b>Task Create Time:</b>
              </Col>
              <Col>{props.task.taskCreateTime}</Col>
            </Row>
            <Row>
              <Col>
                <b>Task Modify Time:</b>
              </Col>
              <Col>{props.task.taskModifyTime}</Col>
            </Row>
          </Container>
        </Modal.Body>
        <Modal.Footer>
          <Link
            className={"align-left btn btn btn-primary"}
            to={"taskedit/" + props.projectId + "/tasks/" + props.task.id}
          >
            Edit
          </Link>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
