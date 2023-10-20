CREATE TABLE public.companies (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,

    state_id UUID NOT NULL,
    corporate_structure_id UUID NOT NULL,
    address_id UUID,

    name VARCHAR(255) NOT NULL,
    area VARCHAR(200),
    area_description TEXT,
    stock_amount DECIMAL(15,2),
    stock_price DECIMAL(15,2),
    is_active BOOLEAN NOT NULL,
    is_register_complete BOOLEAN,
    dissolved_at TIMESTAMP WITH TIME ZONE,

    createddate TIMESTAMP WITH TIME ZONE NOT NULL,
    updateddate TIMESTAMP WITH TIME ZONE,
    deleteddate TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_company_state FOREIGN KEY (state_id) REFERENCES public.states (id),
    CONSTRAINT fk_company_corporate_structure FOREIGN KEY (corporate_structure_id) REFERENCES public.corporate_structures (id),
    CONSTRAINT fk_company_address FOREIGN KEY (address_id) REFERENCES public.address (id)
);

CREATE INDEX idx_company_name ON companies(name);