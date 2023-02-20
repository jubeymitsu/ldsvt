let data;

let answer = fetch("http://localhost:8080/customers")
    .then( (response) => response.json())
    .then(info =>{
        data = info
        // write()
        const template = `<div>${data.length}</div>`;
        document.getElementById('main').innerHTML = template;
    } )

function write(){
    for (let i = 0; i < data.length; i++) {
        document.write("<h4><b>" +data[i].name +"</b></h4>>");
    }
}