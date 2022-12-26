document.getElementById('submitDeposit').addEventListener("click", function deposit() {
    fetch('http://localhost:8080/deposit', {
        method: 'POST',
        headers: {
            'Accept': 'application/json, application/xml, text/plain, text/html, *.*',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'id': document.getElementById('id').value,
            'amount': document.getElementById('amount').value
        })
    }).catch(err =>{alert(err)})
})