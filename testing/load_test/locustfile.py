
from locust import HttpUser, task
from random import Random

class APIUser(HttpUser):
    def get_random_address(self):
        r = Random()
        return "%d.%d.%d.%d" % (r.randint(1, 255), r.randint(1, 255), r.randint(1, 255), r.randint(1, 255))

    @task
    def add_address(self):
        addr = self.get_random_address()
        self.client.post("/address/add?address=%s" % addr)
        self.client.post("/information/add?address=%s&name=A&value=B" % addr)
