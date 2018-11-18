import axios from 'axios'
axios.defaults.withCredentials=true;//让ajax携带cookie

// let host = 'http://xiyoulinux.org:8080/join';
let host = 'http://localhost:8080';


export {axios,host}
