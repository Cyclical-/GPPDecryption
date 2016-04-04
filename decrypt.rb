 require 'rubygems'
require 'openssl'
require 'base64'
def decrypt(password)
  padding = "=" * (4 - (password.length % 4))
  paddedpass = "#{password}#{padding}"
  b64d = Base64.decode64(paddedpass)
  aes = OpenSSL::Cipher::Cipher.new("AES-256-CBC")
  key = "\x4e\x99\x06\xe8\xfc\xb6\x6c\xc9\xfa\xf4\x93\x10\x62\x0f\xfe\xe8\xf4\x96\xe8\x06\xcc\x05\x79\x90\x20\x9b\x09\xa4\x33\xb6\x6c\x1b"
  aes.decrypt
  aes.key = key
  plaintext = aes.update(b64d)
  plaintext << aes.final
  plaintext = plaintext.unpack('v*').pack('C*')
  return plaintext
 end
puts decrypt(ARGV[0])