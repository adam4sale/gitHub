alert("js works");
function postRequests() {
            var request = new XMLHttpRequest();
            request.onreadystatechange = function() {
                if(this.readyState == 4 && this.status == 200){
                    let response = JSON.parse(this.responseText);
                    let data = Object.keys(response[0]);//["name", "body", "id", "amount", "date", "approved"]
                    console.log(data);

                    makeTable(response);
                    createRows(response);

                }
            };
            request.open("POST", "/Requests", true);
            request.send();
}

function makeTable(list) {
    var index = 0;

    for(var x of list){
        console.log(x);
        var form = document.createElement("form");
        form.id = index;
        form.method = "POST";
        form.action="/form/approve";
        document.getElementById("requests").appendChild(form);
        index++;
    }
}
function createHead(list){
var index = 0;
    for(let x of list){

    }
}
function createRows(list){
    var index = 0;
    for(var x of list){

        console.log(JSON.stringify(x));
        var row = document.createElement("tr");
        document.getElementById(index).appendChild(row);
        row.id = "row" + index;

        var name = document.createElement("td");
        name.innerText = "Employee: " + x.name ;
        document.getElementById("row"+index).appendChild(name);

        var amount = document.createElement("td");
                amount.innerText = "|Cost: " + x.amount;
                document.getElementById("row"+index).appendChild(amount);

        var body = document.createElement("p");
        body.name ="body";
        body.innerText = x.body;
        document.getElementById(index).appendChild(body);

        //document.write("<br><br>");


//          button.addEventListener("click", (e) => {
//                                const id = e.target.getAttribute("data-reim-id");
//                                console.log(id);
//                                x.approved = "approved";
//                                fetch(`http://localhost:8080/manager/requests/${id}`, {
//                                    data: JSON.stringify(x)
//                                })
//                               // window.location.reload();
//                            });

        var select = document.createElement("select");
        var approve = document.createElement("option");
        var decline = document.createElement("option");
        approve.innerText = "Approve";
        decline.innerText = "Deny";

        select.name = "approval";
        select.id = "select" + index;
        approve.value = "a " + JSON.stringify(x.body);
        decline.value = "d " + JSON.stringify(x.body);
        document.getElementById(index).appendChild(select);
        document.getElementById("select" + index).appendChild(approve);
        document.getElementById("select" + index).appendChild(decline);

                var button = document.createElement("button");
                button.innerText = "Approve";
                button.setAttribute('data-reim-id', x.id);
                document.getElementById(index).appendChild(button);

        index++;
    }
}
        postRequests();


