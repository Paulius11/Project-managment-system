import React from 'react';
import { Container, Navbar, Col } from 'react-bootstrap';

class Footer extends React.Component {

    render() {

        let year = new Date().getFullYear();

        return (
            <Navbar fixed="bottom" bg="dark" variant="light" >
                <Container>
                    <Col lg={12} className="text-center text-muted">

                        <div>{year}-{year + 1},  All rights deserved to Vilius</div>
                    </Col>


                </Container>
            </Navbar >

        );


    }

}
export default Footer;
