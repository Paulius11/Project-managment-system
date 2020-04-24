import React from 'react';
import { Jumbotron } from 'react-bootstrap';
class Welcome extends React.Component {

    render() {
        return (

            <Jumbotron className="bg-dark text-white">
                <h1>Project management system</h1>
                <p>

                    Welcome to project management system!<br />
                    Currently it's under construction
                </p>
            </Jumbotron >

        )
    }
}
export default Welcome;