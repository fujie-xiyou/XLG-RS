import axios from 'axios'
axios.defaults.withCredentials=true;//让ajax携带cookie

let host = 'http://localhost:8080';


export {axios,host}
