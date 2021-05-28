import axios from 'axios'
import React, { useEffect } from 'react'
import {useState} from 'react'

function CurrencyTest() {
    const [money, setMoney] = useState(0)
    const [gbp, setGBP] = useState(0)

    const from = "USD"
    const to = "PHP"
    const conversion = from+"_"+to
    const url = "https://free.currconv.com/api/v7/convert?q=" + conversion + "&compact=ultra&apiKey=79bbd7ca2e5ef55990e6"

    async function convert() {
        // await axios.get('https://free.currconv.com/api/v7/convert?q=+conversion+&compact=ultra&apiKey=79bbd7ca2e5ef55990e6')
        await axios.get(url)
        
        .then(response => {
            let data = response.data[conversion]
            console.log(data)
            setMoney(data)
        })

        .catch(err => alert(err))
    }
    
    useEffect(() => {
        convert()
    },[])

    return (
        <div>
            {money}
        </div>
    )
}

export default CurrencyTest
