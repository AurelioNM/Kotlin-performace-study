CREATE TABLE public.corporate_structures (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,

    name VARCHAR(20) NOT NULL,

    createddate TIMESTAMP WITH TIME ZONE NOT NULL,
    updateddate TIMESTAMP WITH TIME ZONE,
    deleteddate TIMESTAMP WITH TIME ZONE
);