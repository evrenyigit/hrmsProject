# Human Resource Management System
 HR system for jobs, candidates, and employers

All of the following requirements have been met

IMPORTANT 1: Create dummy services for authentication processes. For example, there is no need to actually send emails.

IMPORTANT 2: Do not code beyond the requirements. This is against the principles of Project Management and Application Lifecycle Management (ALM).

Req 1: Job seekers should be able to register in the system.

Acceptance Criteria:

During registration, users are prompted for their name, surname, national identification number (TC No), birth year, email, password, and password confirmation.
All fields are mandatory, and users are informed accordingly.
Registration is completed by validating the information through MERNIS (a national identification system).
If validation fails, users are notified.
If the email or national identification number is already registered, the registration process is not completed, and users are informed.
Email verification is required for successful registration.

Req 2: Employers should be able to register in the system.

Acceptance Criteria:

During registration, employers are prompted for their company name, website, email (matching the website domain), phone number, password, and password confirmation. This is to ensure that only companies can join the system.
All fields are mandatory, and users are informed accordingly.
Company registrations are validated in two ways. Email verification is required for successful registration, and it also requires approval from HRMS personnel (ours :))
If the email is already registered, the registration process is not completed, and users are informed.

Req 3: General job position names can be added to the system, such as Software Developer, Software Architect.

Acceptance Criteria:

These positions cannot be duplicated, and users are alerted.

Req 4: Employers can be listed. (Full list only)

Req 5: Job seekers can be listed. (Full list only)

Req 6: Job positions can be listed. (Full list only)

Req 7: Employers can add job postings to the system.
• In the job posting form:
• A general job position can be selected from a dropdown list (e.g., Java Developer) (Required)
• Job description can be entered (e.g., proficient in JAVA, C#, etc. for our company) (Required)
• City information can be selected from a dropdown list (Required)
• Minimum and maximum salary range can be entered (Optional)
• Number of open positions (Required)
• Application deadline and quantity can be entered.

Req 8: All active job postings in the system can be listed.
• The list should be presented in a table format.
• The list should include the company name, general job position name, number of open positions, publication date, and application deadline.

Req 9: All active job postings in the system can be listed by date.
• The list should be presented in a table format.
• The list should include the company name, general job position name, number of open positions, publication date, and application deadline.

Req 10: All active job postings for a specific company can be listed.
• The list should be presented in a table format.
• The list should include the company name, general job position name, number of open positions, publication date, and application deadline.

Req 11: Employers can deactivate a job posting in the system. (Inactive posting)



