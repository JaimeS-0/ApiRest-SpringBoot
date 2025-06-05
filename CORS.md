

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h1>Holaaa</h1>

</body>
<script>

    fetch("http://localhost:9000/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: "usuario",
            password: "contraseña"
        })
    })
        .then(res => res.json())
        .then(data => {
            const token = data.token;
            return fetch("http://localhost:9000/api/v1/atracciones", {
                headers: {
                    "Authorization": "Bearer " + token
                }
            });
        })
        .then(res => res.json())
        .then(data => console.log("Atracciones:", data))
        .catch(err => console.error("Error:", err));


</script>

</html>