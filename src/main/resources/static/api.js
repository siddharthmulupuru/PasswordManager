function handleUnauthorized(status) {
    if (status === 401) {
        sessionStorage.clear();
        window.location.href = "/";
        return true;
    }
    return false;
}

// Gets all the user's vault entries as JSON data. Returns null if an error occurs.
async function getVaultEntries() {
    const token = sessionStorage.getItem("token");
    const url = "/api/vault"
    const response = await fetch(url, {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    });

    if (response.status !== 200) {
        handleUnauthorized(response.status);
        return null;
    }

    const data = await response.json();
    return data;
}

// Deletes the vault entry based on the entryId. Returns false if an error occurs and
// true if succeeded.
async function deleteVaultEntry(entryId) {
    const token = sessionStorage.getItem("token");
    const url = "/api/vault/" + entryId;
    const response = await fetch(url, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + token,
        }
    });

    if (response.status !== 204) {
        handleUnauthorized(response.status);
        return false;
    }

    return true;
}

async function updateVaultEntry(entry) {
    const token = sessionStorage.getItem("token");
    const url = "/api/vault/" + entry.id;
    const response = await fetch(url, {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            encryptedName: entry.encryptedName,
            encryptedWebsite: entry.encryptedWebsite,
            encryptedWebsiteUsername: entry.encryptedWebsiteUsername,
            encryptedWebsitePassword: entry.encryptedWebsitePassword,
            encryptedDescription: entry.encryptedDescription
        })
    });

    if (response.status !== 200) {
        handleUnauthorized(response.status);
        return null;
    }

    const data = await response.json();
    return data;
}

async function createVaultEntry(entry) {
    const token = sessionStorage.getItem("token");
    const url = "/api/vault";
    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            encryptedName: entry.encryptedName,
            encryptedWebsite: entry.encryptedWebsite,
            encryptedWebsiteUsername: entry.encryptedWebsiteUsername,
            encryptedWebsitePassword: entry.encryptedWebsitePassword,
            encryptedDescription: entry.encryptedDescription
        })
    });

    if (response.status !== 201) {
        handleUnauthorized(response.status);
        return null;
    }

    const data = await response.json();
    return data;
}

async function changePassword(currentPassword, newPassword, newSalt, reEncryptedEntries) {
    const token = sessionStorage.getItem("token");
    const url = "/api/auth/password";
    const response = await fetch(url, {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            currentPassword: currentPassword,
            newPassword: newPassword,
            newSalt: newSalt,
            entries: reEncryptedEntries
        })
    });

    if (response.status !== 200) {
        handleUnauthorized(response.status);
        return null;
    }

    const data = await response.json();
    return data;
}