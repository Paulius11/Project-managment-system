import React , { useState } from 'react'
import { useDrop } from 'react-dnd'
import { ItemTypes } from './utils/Items'
import axios from 'axios';
export default function TaskBoxTarget(props) {



    function updateTaskState(item, dropBox) {

        const api = `http://localhost:9090/api/projects/${props.projectId}/tasks/${item.id}`;
        let newPriority

        console.log(item);
        console.log(`Target id ${dropBox.targetId}`);

        newPriority = props.boxPriority // Sets new property to task

        const task = {
            taskState: newPriority,
        };
        
        console.log(api);
        console.log(task);

        axios.put(api, task)
            .then(response => {
                if (response.data != null) {
                    console.log("updated" + task)
                    console.log(props)
                    props.getTasks()
                }
            });
                                
    }

    const [{ isOver }, drop] = useDrop({
        accept: ItemTypes.CARD,
        drop: (item, monitor) => {
            updateTaskState(item, monitor)
        },
        collect: (monitor) => ({
            isOver: !!monitor.isOver(),
        }),
    })

    return (
        <>
            <div ref={drop} className={'task-container'} style={isOver ? { backgroundColor: 'rgba(255, 0, 0, 0.09)' } : { backgroundColor: '' }}>
                {props.children}
            </div>
        </>
    )
}
