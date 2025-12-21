class User:
  def __init__(self, user_id, username, email):
    self.user_id = user_id
    self.username = username
    self.email = email

  def to_item(self):
    return {
      "userId": {"S": self.user_id},
      "username": {"S": self.username},
      "email": {"S": self.email},
    }
