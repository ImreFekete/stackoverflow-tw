import React from 'react';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Register() {
  const navigate = useNavigate();
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [popup, setPopup] = useState(false);
  const [PopupMessage, setPopupMessage] = useState('');

  const URL = 'http://localhost:8080/users/add-new';

  const submitRegistration = async () => {
    if (checkDetails()) {
      const res = await fetch(URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: userName,
          password: password,
        }),
      });
      const data = await res.json();
      if (data === 'username exists') {
        setPopup(true);
        setPopupMessage('Username already occupied!');
        setTimeout(() => {
          setPopup(false);
        }, 1000);
      } else if (data > 0) {
        setPopup(true);
        setPopupMessage('Your registration was successful!');
        setTimeout(() => {
          setPopup(false);
          navigate("/")
        }, 1000);
      }
    }
  };

  const checkDetails = () => {
    if (userName.length < 3) {
        setPopup(true);
        setPopupMessage('Username must be at least 3 characters long!');
        setTimeout(() => {
          setPopup(false);
        }, 1000);
      return false;
    }
    if (password.length < 3) {
        setPopup(true);
        setPopupMessage('Password must be at least 3 characters long!');
        setTimeout(() => {
          setPopup(false);
        }, 1000);
      return false;
    }
    if (password !== confirmPassword) {
        setPopup(true);
        setPopupMessage(`Passwords don't match!`);
        setTimeout(() => {
          setPopup(false);
        }, 1000);
      return false;
    }
    return true;
  };

  return (
    <>
      <div className="register">
        <h2>Register</h2>
        <form
          className="registerForm"
          onSubmit={(e) => {
            e.preventDefault();
            submitRegistration();
          }}
        >
          <div className="registerInputContainer">
            <div className="userInput">
              <label>Username:</label>
              <input type="text" onChange={(e) => setUserName(e.target.value)} />
            </div>
          </div>
          <div className="registerInputContainer">
            <div className="userInput">
              <label>Password:</label>
              <input type="password" onChange={(e) => setPassword(e.target.value)} />
            </div>
            <div className="userInput">
              <label>Confirm password:</label>
              <input type="password" onChange={(e) => setConfirmPassword(e.target.value)} />
            </div>
          </div>
          <div className="registerButtonContainer">
            <button type="submit">Register</button>
            <button onClick={() => navigate("/")}>Cancel</button>
          </div>
        </form>
        {popup ? (
        <div className="popup-container">
          <div className="popup-body">
            <h1>{PopupMessage}</h1>
          </div>
        </div>
      ) : null}
      </div>
    </>
  );
}

export default Register;
