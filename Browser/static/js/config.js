import axios from 'axios'
axios.defaults.withCredentials=true;//让ajax携带cookie

let host = 'http://xiyoulinux.org:8080/join';


export {axios,host}
