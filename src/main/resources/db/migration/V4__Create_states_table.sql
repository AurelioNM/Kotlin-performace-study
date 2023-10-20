CREATE TABLE public.states (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,

    name VARCHAR(30) NOT NULL,
    abbreviation VARCHAR(3) NOT NULL,

    createddate TIMESTAMP WITH TIME ZONE NOT NULL,
    updateddate TIMESTAMP WITH TIME ZONE,
    deleteddate TIMESTAMP WITH TIME ZONE
);