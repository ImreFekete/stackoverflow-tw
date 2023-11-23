INSERT INTO answers (answer_text, user_id, question_id, created_at)
VALUES
    ('For front-end, you can''t go wrong with HTML, CSS, and JavaScript.',
     2, -- user_id of WebWizard89
     1, -- question_id of Choosing the Right Programming Language for Web Development
     NOW()),

    ('If you''re leaning towards back-end, consider Python (Django/Flask), Ruby (Ruby on Rails), or Node.js.',
     3, -- user_id of ByteNinja
     1, -- question_id of Choosing the Right Programming Language for Web Development
     NOW()),

    ('Java and C# are also solid choices for full-stack development. Java with Spring Boot or C# with ASP.NET provide powerful tools for building robust applications.',
     4, -- user_id of CodeSmith
     1, -- question_id of Choosing the Right Programming Language for Web Development
     NOW()),

    ('Don''t forget about PHP! It''s been powering the web for years, and frameworks like Laravel can make development faster and more efficient.',
     5, -- user_id of WebSlinger
     1, -- question_id of Choosing the Right Programming Language for Web Development
     NOW()),

    ('Keep in mind your project requirements. If scalability and performance are key, languages like Go and Rust might be worth exploring.',
     6, -- user_id of DataGeek
     1, -- question_id of Choosing the Right Programming Language for Web Development
     NOW()),

    ('Start with strong, unique passwords for every account. Consider using a password manager to keep track of them securely.',
     8, -- user_id of InfoSecGuy
     2, -- question_id of Securing Your Digital Life: Best Practices
     NOW()),

    ('Keep your software up-to-date! Regularly update your operating system, browsers, and applications to patch vulnerabilities.',
     9, -- user_id of FirewallFreak
     2, -- question_id of Securing Your Digital Life: Best Practices
     NOW()),

    ('Beware of phishing emails and suspicious links. Always verify the sender''s identity before clicking or sharing any personal info.',
     10, -- user_id of PhishBuster
     2, -- question_id of Securing Your Digital Life: Best Practices
     NOW()),

    ('Use a reputable VPN when connecting to public Wi-Fi networks to encrypt your internet traffic and keep your data safe from snoopers.',
     11, -- user_id of VPNinja
     2, -- question_id of Securing Your Digital Life: Best Practices
     NOW()),

    ('Invest in reliable antivirus and anti-malware software. Regular scans can catch and remove potential threats before they cause harm.',
     12, -- user_id of MalwareSlayer
     2, -- question_id of Securing Your Digital Life: Best Practices
     NOW()),

    ('Amazon Web Services (AWS) is a popular choice with a wide range of services, strong global presence, and pay-as-you-go pricing.',
     14, -- user_id of CloudSavvy
     3, -- question_id of Cloud Computing Options for Startups
     NOW()),

    ('Microsoft Azure provides seamless integration with Windows environments, great hybrid capabilities, and a generous free tier for startups.',
     15, -- user_id of AzureGuru
     3, -- question_id of Cloud Computing Options for Startups
     NOW()),

    ('Google Cloud Platform (GCP) excels in data analytics and machine learning. Their Kubernetes Engine is top-notch for container management.',
     16, -- user_id of GCPExplorer
     3, -- question_id of Cloud Computing Options for Startups
     NOW()),

    ('Don''t overlook DigitalOcean! It''s user-friendly, cost-effective, and perfect for startups looking to deploy applications quickly.',
     17, -- user_id of DigitalNomad
     3, -- question_id of Cloud Computing Options for Startups
     NOW()),

    ('IBM Cloud offers enterprise-level features with a focus on AI and blockchain technologies. Their AI services can give your startup a competitive edge.',
     18, -- user_id of IBMCloudWhiz
     3, -- question_id of Cloud Computing Options for Startups
     NOW());
