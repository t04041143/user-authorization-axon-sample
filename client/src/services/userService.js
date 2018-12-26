import httpClient from "../utils/request";

export function getUserDetails(userId) {
  return httpClient(`http://localhost:9580/user/details?userId=${userId}`);
}
