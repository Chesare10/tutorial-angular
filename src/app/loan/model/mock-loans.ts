import { LoanPage } from "./LoanPage"

export const LOAN_DATA: LoanPage = {
    content: [
        { 
            id: 1, 
            game:{ id:1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' }},
            client: {id:1, name:'Cliente 1'}, 
            dateLoan: new Date('11/02/2022'), 
            dateReturn: new Date('11/03/2022')
        },
        { 
            id: 2, 
            game:{ id:2, title: 'Juego 2', age: 21, category: { id: 2, name: 'Categoría 2' }, author: { id: 2, name: 'Autor 2', nationality: 'Nacionalidad 2' }},
            client: {id:2, name:'Cliente 2'}, 
            dateLoan: new Date('11/04/2022'), 
            dateReturn: new Date('11/05/2022')
        },
        { 
            id: 3, 
            game:{ id:3, title: 'Juego 3', age: 18, category: { id: 3, name: 'Categoría 3' }, author: { id: 3, name: 'Autor 3', nationality: 'Nacionalidad 3' }},
            client: {id:3, name:'Cliente 3'}, 
            dateLoan: new Date('11/06/2022'), 
            dateReturn: new Date('11/07/2022')
        },
        { 
            id: 4, 
            game:{ id:4, title: 'Juego 4', age: 7, category: { id: 4, name: 'Categoría 4' }, author: { id: 4, name: 'Autor 4', nationality: 'Nacionalidad 4' }},
            client: {id:4, name:'Cliente 4'}, 
            dateLoan: new Date('11/08/2022'), 
            dateReturn: new Date('12/12/2023')
        },
        { 
            id: 5, 
            game:{ id:5, title: 'Juego 5', age: 10, category: { id: 5, name: 'Categoría 5' }, author: { id: 5, name: 'Autor 5', nationality: 'Nacionalidad 5' }},
            client: {id:5, name:'Cliente 5'}, 
            dateLoan: new Date('11/10/2022'), 
            dateReturn: new Date('01/30/2024')
        },
        { 
            id: 6, 
            game:{ id:6, title: 'Juego 6', age: 14, category: { id: 6, name: 'Categoría 6' }, author: { id: 6, name: 'Autor 6', nationality: 'Nacionalidad 6' }},
            client: {id:6, name:'Cliente 6'}, 
            dateLoan: new Date('10/12/2022'), 
            dateReturn: new Date('12/21/2022')
        }
    ],
    pageable: {
        pageNumber:0,
        pageSize:5,
        sort: [
            {property:"id", direction:"ASC"}
        ]
    },
    totalElements:6

}
    