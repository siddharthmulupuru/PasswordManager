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
        return null;
    }

    const data = await response.json();
    return data;
}