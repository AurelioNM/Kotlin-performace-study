CREATE TABLE public.address (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,

    first_address VARCHAR(255) NOT NULL,
    second_address VARCHAR(255),
    country VARCHAR(150) NOT NULL,
    state VARCHAR(150),
    city VARCHAR(150) NOT NULL,
    zipcode VARCHAR(20) NOT NULL,

    createddate TIMESTAMP WITH TIME ZONE NOT NULL,
    updateddate TIMESTAMP WITH TIME ZONE,
    deleteddate TIMESTAMP WITH TIME ZONE
);