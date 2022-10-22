import groovy.text.SimpleTemplateEngine

def simple_template(input, replacements) {
    def engine = new SimpleTemplateEngine()
    def template = engine.createTemplate(input).make(replacements)
    def string_template = template.toString()
    engine = null
    template = null
    return string_template
}

def text = '''Job running by $user was <% print job_status == "success" ? "successful" : job_status == "fail" ? "unsuccessful with error: $error_message " : job_status == "cancel" ? "canceled" : "unknown error" %>'''
def binding_success = [user: 'Julia Stone', job_status: 'success']
def binding_fail = [user: 'Boris Jones', job_status: 'fail', error_message: 'error - file not found']
def binding_cancel = [user: 'Victor Simon', job_status: 'cancel']

println(simple_template(text, binding_fail))
