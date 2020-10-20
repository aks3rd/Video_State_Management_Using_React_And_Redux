class LoginFormComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = { "username": "",
      "password": "",
      "error": { "errorFlag": false, "usernameError": "", "passwordError": "", "serverError": "" }
    };
    this.errorStyle = {
      "color": "red"
    };
    this.passwordChangeEventHandler = this.passwordChangeEventHandler.bind(this);
    this.submitEventHandler = this.submitEventHandler.bind(this);
    this.servletRequestEventHandler = this.servletRequestEventHandler.bind(this);
  }
  usernameChangeEventHandler(ev) {
    this.setState({ "username": ev.target.value });
  }
  passwordChangeEventHandler(ev) {
    this.setState({ "password": ev.target.value });
  }
  submitEventHandler(ev) {
    //   alert("submitEventHandler");
    let errorObject = {
      "errorFlag": false,
      "usernameError": "",
      "passwordError": "",
      "serverError": ""
    };
    let errorFlag = false;
    let username = this.state.username;
    let password = this.state.password;
    if (username.length <= 0) {
      errorObject.usernameError = "Username Required";
      errorFlag = true;
    }
    if (password.length <= 0) {
      errorObject.passwordError = "Password Required";
      errorFlag = true;
    }
    if (username.length > 0 && username.length < 8) {
      errorObject.usernameError = "Username should be greater than or equal to 8 character.";
      errorFlag = true;
    }
    if (password.length > 0 && password.length < 8) {
      errorObject.passwordError = "Password should be greater than or equal to 8 character.";
      errorFlag = true;
    }
    errorObject.errorFlag = errorFlag;
    this.setState({ "error": errorObject });
    return errorFlag;
  }
  servletRequestEventHandler(ev) {
    //  alert("actionEventHandler"+ev.target.name);
    if (!this.submitEventHandler(ev)) {
      var data = { username: this.state.username, password: this.state.password, videoList: null, lastVisitedURL: "" };
      let errorObject = {
        "errorFlag": false,
        "usernameError": "",
        "passwordError": "",
        "serverError": ""
      };
      document.getElementById("serverError").innerHTML = "";
      fetch("service/Login/login", {
        method: "POST",
        mode: "cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json"
        },
        redirect: "follow",
        referrerPolicy: "no-referrer",
        body: JSON.stringify(data)
      }).then(response => response.json()).then(function (data) {
        console.log(data);
        if (data.success) {
          if (data.response.lastVisitedURL.length == 0) window.location.replace("index.jsp");else window.location.replace(data.response.lastVisitedURL);
        } else {
          errorObject.serverError = data.exception;
          document.getElementById("serverError").innerHTML = data.exception;
        }
      }).catch(function (error) {
        console.error("Error:", error);
      });
      this.setState({ "error": errorObject });
    } else {
      alert("Faild");
    }
  }
  postData(url = "", data = {}) {

    postData("service/Login/login", data).then(data => {
      console.log(data);
    }).catch(error => {
      console.error("Error:", error);
    });

    alert("CHala");
    var response = fetch(url, {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json"
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify(data)
    });
    return response.json();
  }

  render() {
    return React.createElement(
      "div",
      null,
      React.createElement(
        "form",
        { className: "text-center border border-light p-5", onSubmit: this.submitEventHandler },
        React.createElement(
          "p",
          { className: "h4 mb-4" },
          "Login"
        ),
        React.createElement("br", null),
        React.createElement(
          "div",
          { className: "form-group" },
          React.createElement("input", { type: "text", className: "form-control form-control-user", value: this.state.username, onChange: this.usernameChangeEventHandler.bind(this), id: "username", placeholder: "Username" }),
          React.createElement(
            "small",
            { id: "usernameError", style: this.errorStyle, className: "form-text text-muted text-lg-left" },
            this.state.error.usernameError
          )
        ),
        React.createElement(
          "div",
          { className: "form-group" },
          React.createElement("input", { type: "password", className: "form-control form-control-user", value: this.state.password, onChange: this.passwordChangeEventHandler, id: "password", placeholder: "Password" }),
          React.createElement(
            "small",
            { id: "passwordError", style: this.errorStyle, className: "form-text text-muted" },
            this.state.error.passwordError
          )
        ),
        React.createElement(
          "span",
          { id: "serverError", style: this.errorStyle, className: "form-text text-muted" },
          this.state.error.serverError
        ),
        React.createElement(
          "div",
          { className: "d-flex justify-content-around" },
          React.createElement(
            "div",
            { className: "form-check" },
            React.createElement("input", { type: "checkbox", className: "form-check-input", id: "formRemember" }),
            React.createElement(
              "label",
              { className: "form-check-label", "for": "formRemember" },
              "\xA0\xA0Remember me"
            )
          ),
          React.createElement(
            "div",
            { className: "form-group" },
            React.createElement(
              "a",
              { href: "" },
              "Forgot password?"
            )
          )
        ),
        React.createElement(
          "button",
          { className: "btn btn-info btn-block my-4", type: "button", onClick: this.servletRequestEventHandler },
          "Login"
        ),
        React.createElement(
          "p",
          null,
          "Not a member?",
          React.createElement(
            "a",
            { href: "" },
            "Register"
          )
        )
      )
    );
  }
}