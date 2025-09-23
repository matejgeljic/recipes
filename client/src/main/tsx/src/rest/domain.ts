export interface SpringBootPagination<T> {
    content: T[]; // The actual data items for the current page
    pageable: {
        sort: {
            empty: boolean;
            sorted: boolean;
            unsorted: boolean;
        };
        offset: number;
        pageNumber: number;
        pageSize: number;
        paged: boolean;
        unpaged: boolean;
    };
    last: boolean; // Whether this is the last page
    totalElements: number; // Total number of items across all pages
    totalPages: number; // Total number of pages
    size: number; // Page size (items per page)
    number: number; // Current page number (zero-based)
    sort: {
        empty: boolean;
        sorted: boolean;
        unsorted: boolean;
    };
    first: boolean; // Whether this is the first page
    numberOfElements: number; // Number of items in the current page
    empty: boolean; // Whether the current page has no items
}