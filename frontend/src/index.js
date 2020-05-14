import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Particles from 'react-particles-js';
ReactDOM.render(
  <React.StrictMode>
    <Particles style={{ position: "absolute" }}
      params={{
        "particles": {
          "number": {
            "value": 50
          },
          "size": {
            "value": 3
          }
        },
        "interactivity": {
          "events": {
            "onhover": {
              "enable": true,
              "mode": "repulse"
            }
          }
        }
      }} />
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

