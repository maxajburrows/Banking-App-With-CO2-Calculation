import {Pie} from 'react-chartjs-2'
import {Chart as ChartJS, ArcElement, Tooltip, Legend, Title} from 'chart.js';


ChartJS.register(ArcElement, Tooltip, Legend, Title);

type PieChartProps = {
    data: Map<string, number>;
    title: string;
}

function Pie2(pieChartProps: PieChartProps) {
    const data = {
        labels: Object.keys(pieChartProps.data),
        datasets: [
            {
                data: Object.values(pieChartProps.data),
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    'rgb(64, 256, 2)'
                ],
                borderWidth: 1,
            },
        ],
    };

    const options = {
        plugins: {
            legend: {
                labels: {
                    color: 'white' // Set the label color here
                }
            },
            title: {
                display: true,
                text: pieChartProps.title,
                color: 'white', // Set the title color here
                font: {
                    size: 20
                }
            },
        }
    };

    return <Pie data={data} options={options}/>

}

export default Pie2;
