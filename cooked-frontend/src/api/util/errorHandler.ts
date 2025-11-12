export function handleApiError(error: any): never {
    if (error.response) {
        const status = error.response.status;
        const message = 
            error.response.data?.message || 
            error.response.data?.error || 
            "An unexpected error occured";

            switch (status) {
                case 400:
                    throw new Error(message || "Bad request. Please check your input.");
                case 401:
                    throw new Error(message || "Invalid credentials. Please try again.");
                case 403:
                    throw new Error("You do not have permission to perform this action.");
                case 404:
                    throw new Error("Resource not found.");
                case 409:
                    throw new Error(message || "Conflict: This resource already exists.");
                case 500:
                    throw new Error("Internal server error. Please try again later.");
                default:
                    throw new Error(message);
            }
    } else if (error.request) {
        throw new Error("No response from server. Please check your connection.")
    } else {
        throw new Error("Something went wrong while setting up the request.")
    }
}