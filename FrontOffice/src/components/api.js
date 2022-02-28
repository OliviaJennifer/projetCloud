import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:9005/api/'
});

export default api;