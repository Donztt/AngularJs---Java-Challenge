app.service("utilService", function () {
  this.showAlert = function (message) {
    var alertDiv = document.createElement("div");
    alertDiv.innerHTML = message;
    alertDiv.style.padding = "10px";
    alertDiv.style.borderRadius = "5px";
    alertDiv.style.backgroundColor = "red";
    alertDiv.style.color = "black";
    alertDiv.style.display = "flex";
    alertDiv.style.alignItems = "center";

    setTimeout(function () {
      alertDiv.style.display = "none";
    }, 5000);

    document.body.prepend(alertDiv);
  };

  this.showFinishOrder = function (message) {
    var alertDiv = document.createElement("div");
    alertDiv.innerHTML = message;
    alertDiv.style.padding = "10px";
    alertDiv.style.borderRadius = "5px";
    alertDiv.style.backgroundColor = "green";
    alertDiv.style.color = "black";
    alertDiv.style.display = "flex";
    alertDiv.style.alignItems = "center";

    setTimeout(function () {
      alertDiv.style.display = "none";
    }, 8000);

    document.body.prepend(alertDiv);
  };
});
