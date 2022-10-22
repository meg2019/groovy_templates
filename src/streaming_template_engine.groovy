import groovy.text.StreamingTemplateEngine

def streaming_template(replacements) {
    def text_template = ''' Job running by ${user} was ${job_status == 'success' ? 'successful, using parameters: subid: '+subid+','+' portid: '+portid+','+' output path: '+path :
                                    job_status == 'fail' ? 'unsuccessful, using parameters: subid: '+subid+' portid: '+portid+' output path: '+path :
                                    job_status == 'cancel' ? 'canceled' : 'unknown error'}'''
    def template = new StreamingTemplateEngine().createTemplate(text_template)
    return template.make(replacements).toString()
}

//def text_template = ''' Job running by ${user} was ${job_status == 'success' ? 'successful' : job_status == 'fail' ? 'unsuccessful with error: ' +error_message : job_status == 'cancel' ? 'canceled' : 'unknown error'}'''
def binding_success = [user: 'Julia Stone', job_status: 'success', subid: '1056', portid: '2', path: 'gs://gsc/out']
def binding_fail = [user: 'Boris Jones', job_status: 'fail', subid: '1057', portid: '3', path: 'gs://aws/outer']
def binding_cancel = [user: 'Victor Simon', job_status: 'cancel']

print(streaming_template(binding_success))