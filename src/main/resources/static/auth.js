// Authenticate the username and password by communicating with the API.
// Returns the output from the authentication (JWT).
async function authenticate(url, username, password) {
    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({username: username, password: password})
    });

    const data = await response.text();
    return data;
}