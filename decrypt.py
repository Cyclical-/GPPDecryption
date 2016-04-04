#!/usr/bin/python
import sys
from Crypto.Cipher import AES
from base64 import b64decode

if(len(sys.argv) != 2):
  print "decrypt.py <cpassword>"
  sys.exit(0)

key = """4e9906e8fcb66cc9faf49310620ffee8f496e806cc057990209b09a433b66c1b""".decode('hex')
cpassword = sys.argv[1]
cpassword += "=" * ((4 - len(cpassword) % 4) % 4)
password = b64decode(cpassword)
out = AES.new(key, AES.MODE_CBC, "\x00" * 16)
out = out.decrypt(password)
print out[:-ord(out[-1])].decode('utf16')