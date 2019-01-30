import httpClient from "../utils/http-client";

export function getSelfDetails() {
  return httpClient.get(`/user/self-details`, {
    headers: { "Authorization": `Token ${localStorage.getItem("token")}` }
  });
}
