// Derives a 256-bit AES-GCM encryption key from a master password and salt using PBKDF2.
// The same password and salt will always produce the same key.
// key is non-extractable — it stays in browser memory and cannot be read by JavaScript.
async function deriveKey(password, salt) {
    const encoder = new TextEncoder();
    const keyMaterial = await crypto.subtle.importKey(
        "raw",
        encoder.encode(password),
        "PBKDF2",
        false,
        ["deriveKey"]
    );

    const key =  await crypto.subtle.deriveKey(
        {
            name: "PBKDF2",
            salt: encoder.encode(salt),
            iterations: 1000000,
            hash: "SHA-256"
        },
        keyMaterial,
        { name: "AES-GCM", length: 256 },
        false,
        ["encrypt", "decrypt"]
    )

    return key;
}

// Encrypts a plaintext string using AES-GCM with the provided key.
// Generates a random IV for each encryption to ensure semantic security.
// Returns an object containing the ciphertext (ArrayBuffer) and IV (Uint8Array).
// The IV must be stored alongside the ciphertext to enable decryption later.
async function encrypt(key, plaintext) {
    const encoder = new TextEncoder();
    const encoded = encoder.encode(plaintext);
    const iv = crypto.getRandomValues(new Uint8Array(12));

    const ciphertext = await window.crypto.subtle.encrypt(
        { name: "AES-GCM", iv: iv },
        key,
        encoded,
    );

    return { iv: iv, ciphertext: ciphertext };
}

// Decrypts an AES-GCM encrypted ciphertext using the provided key and IV.
// The IV must match the one used during encryption.
// Returns the decrypted plaintext as a string.
async function decrypt(key, iv, ciphertext) {
    const decoder = new TextDecoder();

    const plaintext = await window.crypto.subtle.decrypt(
        { name: "AES-GCM", iv: iv},
        key,
        ciphertext,
    );

    return decoder.decode(plaintext);
}

// Generates a cryptographically secure random 16-byte salt encoded as a Base64 string.
// Used for key derivation when registering or changing the master password.
function generateSalt() {
    const saltBytes = crypto.getRandomValues(new Uint8Array(16));
    return toBase64(saltBytes);
}

// Converts an ArrayBuffer or Uint8Array to a Base64 encoded string.
function toBase64(buffer) {
    return btoa(String.fromCharCode(...new Uint8Array(buffer)));
}

// Converts a Base64 encoded string back to a Uint8Array of bytes.
function fromBase64(base64) {
    return Uint8Array.from(atob(base64), c => c.charCodeAt(0));
}

// Encrypts a plaintext string and returns a single Base64 encoded string
// containing the IV prepended to the ciphertext (IV is always first 12 bytes).
async function encryptToString(key, plaintext) {
    const data = await encrypt(key, plaintext);
    const iv = data.iv;
    const ciphertext = data.ciphertext;

    const ivArray = new Uint8Array(iv);
    const ciphertextArray = new Uint8Array(ciphertext);
    const combined = new Uint8Array(ivArray.length + ciphertextArray.length);
    combined.set(ivArray, 0);
    combined.set(ciphertextArray, 12);

    return toBase64(combined);
}

// Decrypts a Base64 encoded string produced by encryptToString.
// Splits the first 12 bytes as IV and the rest as ciphertext, then decrypts.
// Returns the decrypted plaintext as a string.
async function decryptFromString(key, ciphertext) {
    const ciphertextArray = fromBase64(ciphertext);
    const iv = ciphertextArray.slice(0, 12);
    const ciphertextEncrypted = ciphertextArray.slice(12);

    const plaintext = await decrypt(key, iv, ciphertextEncrypted);

    return plaintext;
}