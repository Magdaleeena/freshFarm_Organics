-- V2__seed_dairy_products.sql

-- MILK (Whole, Semi-Skimmed, Skimmed, Jersey) with 1 Pint and 2 Pints sizes
INSERT INTO products (product_name, unit_price, unit_value, unit_type, category, description, image_url) VALUES
('Whole Milk (1 Pint)', 0.80, 0.568, 'litre', 'milk', 'Fresh whole milk, 1 pint', 'https://themodernmilkman.co.uk/_next/image?url=https%3A%2F%2Fimages.ctfassets.net%2Fszr69flitpp6%2F5VB3rtCP6CW3dIJEy3NgT1%2F958364fe279865f4aaed28fc30061bc9%2F7_March-090.jpg&w=1200&q=75'),
('Whole Milk (2 Pints)', 1.50, 1.136, 'litre', 'milk', 'Fresh whole milk, 2 pints', 'https://themodernmilkman.co.uk/_next/image?url=https%3A%2F%2Fimages.ctfassets.net%2Fszr69flitpp6%2F5VB3rtCP6CW3dIJEy3NgT1%2F958364fe279865f4aaed28fc30061bc9%2F7_March-090.jpg&w=1200&q=75'),
('Semi-Skimmed Milk (1 Pint)', 0.75, 0.568, 'litre', 'milk', 'Fresh semi-skimmed milk, 1 pint', 'https://themodernmilkman.co.uk/_next/image?url=https%3A%2F%2Fimages.ctfassets.net%2Fszr69flitpp6%2F67x3U3PMupOrAgjjrU7Tza%2Fc88b0f58b2de32ad30b373de3daaf8ec%2F7_March-095.jpg&w=1200&q=75'),
('Semi-Skimmed Milk (2 Pints)', 1.40, 1.136, 'litre', 'milk', 'Fresh semi-skimmed milk, 2 pints', 'https://themodernmilkman.co.uk/_next/image?url=https%3A%2F%2Fimages.ctfassets.net%2Fszr69flitpp6%2F67x3U3PMupOrAgjjrU7Tza%2Fc88b0f58b2de32ad30b373de3daaf8ec%2F7_March-095.jpg&w=1200&q=75'),
('Skimmed Milk (1 Pint)', 0.70, 0.568, 'litre', 'milk', 'Fresh skimmed milk, 1 pint', 'https://themodernmilkman.co.uk/_next/image?url=https%3A%2F%2Fimages.ctfassets.net%2Fszr69flitpp6%2F1k1fp9motdZP9p5TxJ3qij%2F6bcebb5daafbd36f0a9e663752cbab66%2F7_March-099.jpg&w=1200&q=75'),
('Skimmed Milk (2 Pints)', 1.30, 1.136, 'litre', 'milk', 'Fresh skimmed milk, 2 pints', 'https://themodernmilkman.co.uk/_next/image?url=https%3A%2F%2Fimages.ctfassets.net%2Fszr69flitpp6%2F1k1fp9motdZP9p5TxJ3qij%2F6bcebb5daafbd36f0a9e663752cbab66%2F7_March-099.jpg&w=1200&q=75'),
('Jersey Milk (1 Pint)', 1.10, 0.568, 'litre', 'milk', 'Rich Jersey milk, 1 pint', 'https://themodernmilkman.co.uk/_next/image?url=https%3A%2F%2Fimages.ctfassets.net%2Fszr69flitpp6%2F6HGKX74rNiu19PcD6vLGF1%2Fee736e66047b10ff89aecfb582607565%2FChannel_Island_Milk.png&w=1200&q=75'),
('Jersey Milk (2 Pints)', 2.00, 1.136, 'litre', 'milk', 'Rich Jersey milk, 2 pints', 'https://themodernmilkman.co.uk/_next/image?url=https%3A%2F%2Fimages.ctfassets.net%2Fszr69flitpp6%2F6HGKX74rNiu19PcD6vLGF1%2Fee736e66047b10ff89aecfb582607565%2FChannel_Island_Milk.png&w=1200&q=75');

-- CHEESE
INSERT INTO products (product_name, unit_price, unit_value, unit_type, category, description, image_url) VALUES
('Cheddar Cheese (200g)', 8.00, 0.2, 'kg', 'cheese', 'Mature British Cheddar cheese, 200g block', 'https://www.thecourtyarddairy.co.uk/app/uploads/st-andrew-s-cheddar-460x460.jpg'),
('Red Leicester Cheese (200g)', 8.50, 0.2, 'kg', 'cheese', 'Classic Red Leicester cheese, 200g block', 'https://www.thecourtyarddairy.co.uk/app/uploads/sparkenhoe-vintage-red-leicester-cheese.jpg'),
('Goat cheese (200g)', 2.70, 0.2, 'kg', 'cheese', 'Goat Milk Gouda, 200g block', 'https://www.thecourtyarddairy.co.uk/app/uploads/killeen-goats-cheese-gouda.jpg'),
('Brie Cheese (200g)', 2.80, 0.2, 'kg', 'cheese', 'Soft Brie Cheese, 200g block', 'https://www.thecourtyarddairy.co.uk/app/uploads/baron-bigod-brie-cheese-460x460.jpg'),
('Devon Blue Cheese (200g)', 3.00, 0.2, 'kg', 'cheese', 'British Devon Blue cheese, 200g block', 'https://www.thecourtyarddairy.co.uk/app/uploads/devon-blue-cheese-1-460x460.jpg'),

-- YOGHURT
INSERT INTO products (product_name, unit_price, unit_value, unit_type, category, description, image_url) VALUES
('Natural Yoghurt (Plain, 500g)', 1.20, 0.5, 'kg', 'yoghurt', 'Plain natural yoghurt, 500g tub', 'https://ecom-su-static-prod.wtrecom.com/images/products/11/LN_053023_BP_11.jpg'),
('Natural Yoghurt (Strawberry, 500g)', 1.40, 0.5, 'kg', 'yoghurt', 'Strawberry flavoured natural yoghurt, 500g tub', 'https://ecom-su-static-prod.wtrecom.com/images/products/11/LN_053024_BP_11.jpg'),
('Natural Yoghurt (Raspberry, 500g)', 1.40, 0.5, 'kg', 'yoghurt', 'Raspberry flavoured natural yoghurt, 500g tub', 'https://ecom-su-static-prod.wtrecom.com/images/products/11/LN_086076_BP_11.jpg'),
('Greek Yoghurt (Plain, 500g)', 1.50, 0.5, 'kg', 'yoghurt', 'Plain Greek yoghurt, 500g tub', 'https://ecom-su-static-prod.wtrecom.com/images/products/11/LN_884226_BP_11.jpg'),
('Greek Yoghurt (Strawberry & Passion Fruit, 500g)', 1.70, 0.5, 'kg', 'yoghurt', 'Greek yoghurt with strawberry and passion fruit, 500g tub', 'https://ecom-su-static-prod.wtrecom.com/images/products/11/LN_911837_BP_11.jpg'),
('Greek Yoghurt (Mango, 500g)', 1.70, 0.5, 'kg', 'yoghurt', 'Mango flavoured Greek yoghurt, 500g tub', 'https://ecom-su-static-prod.wtrecom.com/images/products/11/LN_999267_BP_11.jpg');