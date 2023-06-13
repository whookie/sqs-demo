
from locust import HttpUser, task
from random import Random

class APIUser(HttpUser):
    def get_random_address(self):
        r = Random()
        return "%d.%d.%d.%d" % (r.randint(1, 255), r.randint(1, 255), r.randint(1, 255), r.randint(1, 255))

    @task
    def add_address(self):

        # Note that we explicitly avoid flooding the /information/get URL,
        # because it accesses a 3rd party site. We do not want to flood the 3rd party site.

        addr = self.get_random_address()
        self.client.post("/information/add?address=%s&name=A&value=B" % addr)
        self.client.delete("/information/delete?address=%s&name=A" % addr)
